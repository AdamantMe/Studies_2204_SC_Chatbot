(ns project-chatbot.core
  (:require [project-chatbot.process-input :as input]
            [project-chatbot.process-output :as output]
            [clojure.string :as string])
  (:gen-class))

;; The part in control of interactions with the user. Greeting, a loop of questions - answers, ending.
(defn chatbot-init []

  (println "Hello, please enter your name?")
  (def username (read-line))
  (println "Hello" username) (println "please ask me a question about parks in Prague.")

  (loop [last_input (read-line)]
    (if (not (nil? (input/process_input_string last_input)))
      (output/process_output_string)
      (if (not (= (string/lower-case last_input) "no"))
        (println "Apologies, I could not Understand your question.") 
        (println "Please ask something else or type No to end chat")))
    (if (= last_input "no") (println "Goodbye")

        (recur (read-line)))))

;; Patient zero, all started with it.
(defn -main [& args]
  (chatbot-init))