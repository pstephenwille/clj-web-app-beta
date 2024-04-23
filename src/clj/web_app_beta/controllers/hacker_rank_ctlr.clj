(ns web-app-beta.controllers.hacker-rank-ctlr
(:require
  [web-app-beta.config :refer [env]]
  [clj-http.client :as client]))


(defn get-hacker-rank-data [url]
  (let [resp (client/get url)]
    (cond (= (client/success? resp) true)
          (:body resp)
          :else "eek!")))

(defn call-hacker-rank []
  (def top-stories-url (str (env :hacker-rank-base-url) "topstories.json"))
  (def resp (get-hacker-rank-data top-stories-url))
  (list resp)
  )

(defn build-item-URIs [URIs]
  (map #(str "item/" % ".json") URIs))
