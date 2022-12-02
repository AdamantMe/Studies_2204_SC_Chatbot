(ns project-chatbot.process-input
  (:require [clojure.string :as string]
            [project-chatbot.data-ops :as data]))

(defn process_input_string [input_string]
  ;; (println "Processing input...")
  (data/last-query-set-park :Bertramka) ;; Temporary hardcoded, only for the prototype

  (let [input_keywords (map symbol (re-seq #"ride|riding|bike|biking|skate|skating|sport|sports|play|playing|playground|get to|get from|transport|transportation|parking|park a car|wc|toilet|restroom|dog|dogs|attractions|interesting|things to do|food|eat|restaurant|ski|skiing" input_string))]

    (data/last-query-set-activity (data/get-synonym-from-keyword input_keywords))))





;; (defn test [str1]
;;   (for [t str1]

;;     (re-seq (re-pattern "[^0-9]*trams*[.]* +[Nn]o.[0-9, ]+") t))

;;   )

;; (println (test "Václavské náměstí trams. No. 3, 9, 14, 24, 51, 52, 54, 55, 56, 58, metro A, B Můstek"))

;; (def testString "Can I ride a bike there?")

