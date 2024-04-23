(ns web-app-beta.routes.home
  (:require
    [web-app-beta.layout :as layout]
    [clojure.java.io :as io]
    [web-app-beta.middleware :as middleware]
    [ring.util.response]
    [ring.util.http-response :as response]
    [web-app-beta.controllers.hacker-rank-ctlr :as hacker-rank-ctlr]))




(defn home-page [request]
  (layout/render request "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(defn about-page [request]
  (layout/render request "about.html"))

(defn hacker-rank-page [request]
  (def resp (hacker-rank-ctlr/call-hacker-rank))
  (layout/render request "hacker-rank.html" {:data resp}))

(defn home-routes []
  [""
   {:middleware [middleware/wrap-csrf
                 middleware/wrap-formats]}

   ["/" {:get home-page}]
   ["/hacker-rank", {:get hacker-rank-page}]
   ["/about" {:get about-page}]])

