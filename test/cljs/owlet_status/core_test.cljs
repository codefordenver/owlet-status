(ns owlet-status.core-test
  (:require [cljs.test :refer-macros [is are deftest testing use-fixtures]]
            [reagent.core :as reagent :refer [atom]]
            [owlet-status.core :as rc]))

(deftest test-home
  (is (= true true)))

