(ns project-chatbot.core-test
  (:require [clojure.test :refer :all]
            [project-chatbot.core :refer :all]
            [project-chatbot.data-ops :as data]))

(deftest test-get-synonym-from-keyword-1
  (testing "Can not find a correct synonym for the keyword"
    (is (= (data/get-synonym-from-keyword '(ride)) :biking))))