(ns project-chatbot.process-input
  (:require [project-chatbot.data-ops :as data]
            [clojure.string :as str]))

;; Receives user's question, represented as a raw string. 
;; Returns important keywords extrapolated from that question.
(defn process_input_string [input_string]
  ;; (data/last-query-set-park :bertramka) ;; Temporary hardcoded, only for the prototype

  (let [input_park (map symbol (re-seq #"bertramka|frantiskanska|obora|kampa|kinskeho|klamovka|ladronka|letna|petrin|riegrovy|stromovka|vysehrad" (str/lower-case input_string)))]
    (if (not (empty? input_park))
      ;; Once correct park is identified, set it as user's "Last Querry"'s park to access it easier later on.

      (data/last-query-set-park (data/get-parkname-synonym-from-keyword input_park))
      nil))

  (let [input_keywords (map symbol (re-seq #"ride|riding|bike|biking|skate|skating|sport|sports|soccer|football|frisbee|play|playing|playground|get to|get from|get there|transport|transportation|parking|park a car|wc|toilet|restroom|dog|dogs|attractions|interesting|things to do|food|to eat|restaurant|ski|skiing" input_string))]
    (if (not (empty? input_keywords))
      ;; Once correct activity is identified and made "JSON friendly", set it as user's "Last Querry"'s activity to access it easier later on.
      (data/last-query-set-activity (data/get-activity-synonym-from-keyword input_keywords))
      nil)))