(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]))

;; Matcher copy paste
(defmatch apply-rule [facts]
  ((rule ?n ??antecedents => ??consequents)
   :=> (mfor* [(? antecedents) facts]
              (mout (? consequents)))))

(defrecord Question [keywords])
(defn init-question []
  (Question. (ref '())))
(def question (init-question))

(def test2
  '((Bertramka ride)))

(defn process_output_string [string]
  (println "Keywords extrapolated from the input:" string)

  (println question)
  (dosync (ref-set (:keywords question) (list string)))
  (println question)
  (println (deref (:keywords question)))
  (println "INITIATING TESTS")

  (let [all_keywords (list string)]

    ;; Does NOT work
    (println "test1:")
    (println all_keywords)
    (println (type all_keywords))
    (println (apply-rule
              '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
              all_keywords))

    ;; Does work
    (println "test2:")
    (println test2)
    (println (type test2))
    (println (apply-rule
              '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
              test2))

    ;; Does NOT work
    (println "test3:")
    (println (deref (:keywords question)))
    (println (type (deref (:keywords question))))
    (println (apply-rule
              '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
              (deref (:keywords question))))


    (println "...done")))