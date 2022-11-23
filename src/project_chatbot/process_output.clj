(ns project-chatbot.process-output
  (:use [org.clojars.cognesence.matcher.core])
  (:require [clojure.string :as string]))

(defmatch apply-rule [facts]
  ((rule ?n ??antecedents => ??consequents)
   :=> (mfor* [(? antecedents) facts]
              (println (mout (? consequents))))))

(defrecord Question [keywords])
(defn init-question []
  (Question. (ref '())))
(def question (init-question))

(defn in?
  [coll elm]
  (some #(= elm %) coll))

(def facts
  '((ride Bertramka)
    (bike Bertramka)
    (Bertramka ride)
    (Bertramka ride bike)))

(def rules
  '((rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
    (rule 1 (ride ?location) => ())
    ;; (rule 2 (Bertramka ?action) => ())
    (rule 5 (big ?x)   => (heavy ?x))
    (rule 4 (light ?x) => (portable ?x))
    (rule 3 (small ?x) => (light ?x))))

(def test2
  '(Bertramka ride))

(def test3
  '((Bertramka ride)))

(defn apply-all [rules facts]
  (reduce concat
          (map #(apply-rule % facts) rules)))

(defn process_output_string [string]
  (println test3)

  (println question)
  (dosync (ref-set (:keywords question) (list (conj string "Bertramka"))))
  (println question)

  (println "1")
  (println (apply-rule
            '(rule 0 (Bertramka ?ride) => (Yes, you can ride a bike in Bertramka))
            test2))

  (println "2")
  (println test3)
  (println (apply-rule
            '(rule 0 (Bertramka ?ride) => (Yes, you can ride a bike in Bertramka))
            test3))

  (println "4")
  (println (apply-rule
            '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
            test3))

  (let [all_keywords (conj (list (conj string "Bertramka")) " ")]

    (println "5")

    (println all_keywords)

    (println "7")
    (println (deref (:keywords question)))
    (println (apply-rule 
              '(rule 0 (Bertramka ride) => (Yes, you can ride a bike in Bertramka))
              (deref (:keywords question))))
    ;; (println (apply-all rules all_keywords))

  ;; Yes, you can ride a bike in Bertramka

    ;; ((rule ?n ??antecedents => ??consequents)
    ;;  :=> (mfor* [(? antecedents) facts]
    ;;             (mout (? consequents))))





    (println "...done")))





;; (m/mlet ['(?x ?y ?z) '(cat dog bat)]
;;       (? y))



;; (defmatch math1 []
;;   ((?x plus ?y)  :=> (+ (? x) (? y)))
;;   ((?x minus ?y) :=> (- (? x) (? y))))

;; (math1 '(4 plus 5)) ; ? 9

;; (math1 '(4 minus 5)) ; ? -1

;; (math1 '(4 times 5)) ; ? nil


;; (defn test []


;;   (mfind ['["Bertramka" (re-seq #"ride|bike" "Can I ride a bike there?")] replies] (? (re-seq #"ride|bike" "Can I ride a bike there?"))))

;; (test)