(ns project-chatbot.process-input
  (:require [clojure.string :as string]))

(defn process_input_string [input_string]
  (println "Processing input...")
  (re-seq #"Bertramka|ride" input_string)
  ;; (string/replace input_string #"x" "modified")
  )





;; (defn test [str1]
;;   (for [t str1]
    
;;     (re-seq (re-pattern "[^0-9]*trams*[.]* +[Nn]o.[0-9, ]+") t))
  
;;   )

;; (println (test "Václavské náměstí trams. No. 3, 9, 14, 24, 51, 52, 54, 55, 56, 58, metro A, B Můstek"))

;; (def testString "Can I ride a bike there?")

