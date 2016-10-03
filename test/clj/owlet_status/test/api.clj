(ns owlet-status.test.api
  (:require [clojure.test :refer :all]
            [ring.mock.request]
            [owlet-status.routes.api :refer :all]))

(deftest test-api

  (testing "get-flag fn"

    (testing "when linux"
      (with-redefs-fn {#'osname (fn [] "Linux")}
        #(is (= (get-flag) "-w"))))

    (testing "when mac os x"
      (with-redefs-fn {#'osname (fn [] "Mac OS X")}
        #(is (= (get-flag) "-t"))))))