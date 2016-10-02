(ns owlet-status.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [owlet-status.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[owlet-status started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[owlet-status has shut down successfully]=-"))
   :middleware wrap-dev})
