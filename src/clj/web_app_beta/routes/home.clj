(ns web-app-beta.routes.home
  (:require
    [web-app-beta.layout :as layout]
    [clojure.java.io :as io]
    [web-app-beta.middleware :as middleware]
    [ring.util.response]
    [ring.util.http-response :as response]
    [clj-http.client :as client]))


(defn get-test-data [url]
  (let [resp (client/get url)]
    (cond (= (client/success? resp) true)
          (:body resp)
          :else "eek!")))


(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn test-data [request]
  (def test-url "https://jsonplaceholder.typicode.com/posts/1")
  (def resp (get-test-data test-url))
  (layout/render request "test-data.html" {:data resp}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}

   ["/" {:get home-page}]
   ["/test-data", {:get test-data}]
   ["/about" {:get about-page}]])

