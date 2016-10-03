(ns owlet-status.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [owlet-status.layout :refer [error-page]]
            [owlet-status.routes.home :refer [home-routes]]
            [owlet-status.routes.api :refer [api-routes]]
            [compojure.route :as route]
            [owlet-status.env :refer [defaults]]
            [mount.core :as mount]
            [owlet-status.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    (->
      #'api-routes
      (wrap-routes middleware/wrap-formats))
    (-> #'home-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
