(ns project-chatbot.core
  (:require [project-chatbot.process-input :as input]
            [project-chatbot.process-output :as output]
            [project-chatbot.helper-functions :as helper]
            [project-chatbot.birds-identification :as birds]
            [clojure.string :as string])
  (:gen-class))

;; The part in control of interactions with the user. Greeting, a loop of questions - answers, ending.
(defn chatbot-init []
  (println "-> Please enter your name.")
  (println "\n-> Hello," (read-line))
  (println "-> Please ask me a question about parks in Prague")
  (println "-> I can also help in identifying birds, type \"Birds\" to enter the Indentification Mode")
  (println "-> You can type \"End\" at any time to end the chat\n")

  (loop [last_input (read-line)]
    (if (helper/contains-bird? last_input)
      (birds/init-identify-bird)
      (do
        (if (not (nil? (input/process_input_string last_input)))
          (output/process_output_string)
          (if (not (= (string/lower-case last_input) "end"))
            (do (println "-> Apologies, I could not understand your question.")
                (println "-> Please ask something else or type \"End\" to end the chat\n"))))
        (if (= (string/lower-case last_input) "end") (println "-> Thank you, goodbye")
            (recur (read-line)))))))

;; Patient zero, all started with it.
(defn -main [& args]
  (chatbot-init))