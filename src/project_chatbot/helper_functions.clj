(ns project-chatbot.helper-functions)


;;Helper function to identify the word bird or birds in a string

(defn contains-bird? [string]
  (re-find #"\b(bird|birds)\b" (clojure.string/lower-case string)))