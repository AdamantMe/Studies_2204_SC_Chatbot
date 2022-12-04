(ns project-chatbot.core-test
  (:require [clojure.test :refer :all]
            [project-chatbot.core :refer :all]
            [project-chatbot.data-ops :as data]))

(data/last-query-set-park :bertramka)

(deftest test-get-synonym-from-keyword-1
  (testing "Can not find a correct synonym for the keyword"
    (is (= (data/get-synonym-from-keyword '(ride)) :biking))))

(ns project-chatbot.core-test
  (:require [clojure.test :refer :all]
            [project-chatbot.core :refer :all]
            [project-chatbot.data-ops :as data]))

(def test_keyword_long
  (map symbol (re-seq #"get to" "get to")))

(deftest test-get-synonym-from-keyword-1
  (testing "Can not find a correct synonym for the keyword"
    (is (= (data/get-synonym-from-keyword '(ride)) :biking))))

(deftest test-get-synonym-from-keyword-2
  (testing "Can not find a correct synonym for the keyword"
    (is (= (data/get-synonym-from-keyword test_keyword_long) :transportation))))

(deftest test-get-activity-availability-1
  (testing "Error receiving activity's availability from the JSON file"
    (is (= (data/get-activity-availability :nonexistent) nil))))

(deftest test-get-activity-availability-2
  (testing "Error receiving activity's availability from the JSON file"
    (is (= (data/get-activity-availability :biking) true))))