(ns user
  (:require [mount.core :as mount]
            [owlet-status.figwheel :refer [start-fw stop-fw cljs]]
            owlet-status.core))

(defn start []
  (mount/start-without #'owlet-status.core/repl-server))

(defn stop []
  (mount/stop-except #'owlet-status.core/repl-server))

(defn restart []
  (stop)
  (start))


