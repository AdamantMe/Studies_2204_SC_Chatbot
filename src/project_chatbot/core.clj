(ns project-chatbot.core
  (:require [project-chatbot.process-input :as input]
            [project-chatbot.process-output :as output]
            [project-chatbot.data-ops :as data])
  (:gen-class))

(defn chatbot-init []
  (data/last-query-set-park :Bertramka) ;; temporary hardcoded
  (data/last-query-set-activity :riding) ;; temporary hardcoded

  (loop [last_input (read-line)]
    (let [keywords (input/process_input_string last_input)]

      (output/process_output_string keywords))
    (recur (read-line))))

(defn -main [& args]
  (chatbot-init))
