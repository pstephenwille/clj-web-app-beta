(ns web-app-beta.handler-test
  (:require
    [web-app-beta.controllers.hacker-rank-ctlr :as hacker-rank-ctlr]
    [clojure.test :refer :all]
    [ring.mock.request :refer :all]
    [web-app-beta.handler :refer :all]
    [web-app-beta.middleware.formats :as formats]
    [muuntaja.core :as m]
    [mount.core :as mount]))

(defn parse-json [body]
  (m/decode formats/instance "application/json" body))

(use-fixtures
  :once
  (fn [f]
    (mount/start #'web-app-beta.config/env
                 #'web-app-beta.handler/app-routes)
    (f)))

(deftest test-app
  (testing "main route"
    (let [response ((app) (request :get "/"))]
      (is (= 200 (:status response)))))

  (testing "not-found route"
    (let [response ((app) (request :get "/invalid"))]
      (is (= 404 (:status response))))))

(deftest hacker-rank
  (testing "hacker-rank"
    (let [resp (hacker-rank-ctlr/build-item-URIs [40126751, 40115554, 40127124, 40121318])]
      (println resp)
      )))











