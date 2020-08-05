(defproject xmltojson "0.2.0"
  :description "cloned from github "
  :url "https://github.com/florin-va-oltean/clj-xmltojson.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repositories [["releases" {
                              :url "https://repo.deps.co/quasar-software-research/releases"
                              :sign-releases false
                              :username :env/deps_key
                              :password :env/deps_secret}]
                 ["snapshots" {
                               :url "https://repo.deps.co/quasar-software-research/snapshots"
                               :sign-releases false
                               :username :env/deps_key
                               :password :env/deps_secret}]]
  :profiles {:uberjar {}
             :dev     [:profiles/dev]
             :test    [:profiles/dev :profiles/test]
             :profiles/dev {:dependencies [[pjstadig/humane-test-output "0.10.0"]
                                           [org.clojure/data.xml "0.0.8"]
                                           [com.rpl/specter "1.1.3"]
                                           [com.taoensso/timbre "4.10.0"]
                                           [metosin/jsonista "0.2.5"]
                                           [seancorfield/next.jdbc "1.0.13"] ;; using this to fetch everything from postgres for big tests.
                                           [org.postgresql/postgresql "42.2.9"]]
                            :resource-paths ["dev-resources/jython-standalone.jar"]
                            :plugins []
                            :injections [(require 'pjstadig.humane-test-output)
                                         (pjstadig.humane-test-output/activate!)]}
             :profiles/test {}}
  :test-selectors {:wip (fn [m]
                          (or (clojure.string/includes? (str (:ns m)) "wip")
                              (clojure.string/includes? (str (:name m)) "wip")))})
