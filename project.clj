(defproject project_chatbot "0.1.0-SNAPSHOT"
  :description "Chatbot by Clojure"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clojars.cognesence/matcher "1.0.1"]
                 [org.clojure/data.json "2.4.0"]]
  :main ^:skip-aot project-chatbot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
