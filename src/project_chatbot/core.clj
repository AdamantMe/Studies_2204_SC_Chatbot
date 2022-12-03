(ns project-chatbot.core
  (:require [project-chatbot.process-input :as input]
            [project-chatbot.process-output :as output]
            [project-chatbot.data-ops :as data])
  (:gen-class))

(defn chatbot-init []
  
  (println "Hello, please enter your name?")
  (def username (read-line))
  (println "Hello" username", please ask me a question about parks in Prague.") 
  
  (loop [last_input (read-line)]
    (if (not (nil? (input/process_input_string last_input)))
      (output/process_output_string)
      (println "Apologies, could not process the input. Please ask something else."))

    (recur (read-line))))

(defn -main [& args]
  (chatbot-init))