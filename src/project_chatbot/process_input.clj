(ns project-chatbot.process-input
  (:require [project-chatbot.data-ops :as data]))

(defn process_input_string [input_string]
  (data/last-query-set-park :bertramka) ;; Temporary hardcoded, only for the prototype

  (let [input_keywords (map symbol (re-seq #"ride|riding|bike|biking|skate|skating|sport|sports|play|playing|playground|get to|get from|transport|transportation|parking|park a car|wc|toilet|restroom|dog|dogs|attractions|interesting|things to do|food|eat|restaurant|ski|skiing" input_string))]

    (if (not (empty? input_keywords))
      (data/last-query-set-activity (data/get-synonym-from-keyword input_keywords))
      nil)))