(ns owlet-status.routes.api
  (:require [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer :all]
            [compojure.api.sweet :refer [context]])
  (use [clojure.java.shell :only [sh]]))

(defn get-flag
  "depending on OS"
  []
  (let [os (System/getProperty "os.name")]
    (case os
      "Linux"    "-w"
      "Mac OS X" "-t"
      :default   "-w")))

(defn timed-ping
  "Time a .isReachable ping to a given domain"
  [domain & [timeout]]
  (let [t (str (or timeout 5))
        start (. System (nanoTime))
        ping! (sh "ping" "-c" "1" (get-flag) t domain)
        total (/ (double (- (. System (nanoTime)) start)) 1000000.0)]
    {:time total :result ping!}))


(defn handle-timed-ping-request [req]
  "Note timeout param is in seconds"
  (let [domain (get-in req [:route-params :domain])
        {:keys [time result]} (timed-ping domain)]
    (ok {:time   time
         :result result})))

(defroutes api-routes
           (context "/api" []
                    (GET "/ping/:domain" [] handle-timed-ping-request)))
