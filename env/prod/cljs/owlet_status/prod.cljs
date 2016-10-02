(ns owlet-status.app
  (:require [owlet-status.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
