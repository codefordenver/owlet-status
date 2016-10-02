(ns owlet-status.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[owlet-status started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[owlet-status has shut down successfully]=-"))
   :middleware identity})
