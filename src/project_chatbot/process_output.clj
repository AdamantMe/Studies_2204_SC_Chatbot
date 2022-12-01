(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]
            [project-chatbot.data-ops :as data]))

;; (def rules
;;   '((rule 1 (Bertramka riding true) => (Yes, you can ride a bike in Bertramka))
;;     (rule 2 (Bertramka riding false) => (No, you can not ride a bike in Bertramka))))

;; (def rules
;;   '((rule 1 (riding true) => Yes, you can ride a bike in)
;;     (rule 2 (riding false) => No, you can not ride a bike in)))

(defmatch test1 []
  ((riding true ?park) :=> (mout '(Yes, you can ride a bike in ?park)))
  ((riding false ?park) :=> (mout '(No, you can not ride a bike in ?park))))

(defn process_output_string [string]
  (println "Start")

  ;; (println (apply-rule
  ;;           rules
  ;;           (list (list (symbol (deref (:activity data/LastQuery))) :true))) (str (deref (:park data/LastQuery))))
  (println (list (symbol (deref (:activity data/LastQuery))) true))
  (println (string/join " " (map name (test1 (list (symbol (deref (:activity data/LastQuery))) false (deref (:park data/LastQuery)))))))

  (println "End..."))



















    ;; (println (apply-rule
    ;;           '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
    ;;           (deref (:keywords question))))