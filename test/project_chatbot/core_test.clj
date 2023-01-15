(ns project-chatbot.core-test
  (:require [clojure.test :refer :all]
            [project-chatbot.core :refer :all]
            [project-chatbot.data-ops :as data]
            [project-chatbot.process-input :as input]))

(data/last-query-set-park :bertramka)

(def test_keyword_long
  (map symbol (re-seq #"get to" "get to")))

(deftest test-get-activity-synonym-from-keyword-1
  (testing "Can not find a correct synonym for the activity keyword"
    (is (= (data/get-activity-synonym-from-keyword '(ride)) :biking))))

(deftest test-get-activity-synonym-from-keyword-2
  (testing "Can not find a correct synonym for the activity keyword"
    (is (= (data/get-activity-synonym-from-keyword test_keyword_long) :transportation))))

(deftest test-get-parkname-synonym-from-keyword
  (testing "Can not find a correct synonym for the park name keyword"
    (is (= (data/get-parkname-synonym-from-keyword '(obora)) :obora-hvezda))))

(deftest test-get-activity-availability-1
  (testing "Error receiving activity's availability from the JSON file"
    (is (= (data/get-activity-availability :nonexistent) nil))))

(deftest test-get-activity-availability-2
  (testing "Error receiving activity's availability from the JSON file"
    (is (= (data/get-activity-availability :biking) true))))

(deftest test-input-1
  (testing "Error identifying the park in the question"
    (input/process_input_string "Are there dogs in Letna")
    (is (= (name (deref (:park data/LastQuery))) "letna"))))

(deftest test-input-2
  (testing "Error identifying the activity in the question"
    (input/process_input_string "Are there dogs in Letna")
    (is (= (deref (:activity data/LastQuery)) :dogs))))