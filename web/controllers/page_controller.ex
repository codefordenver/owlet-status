defmodule Web.PageController do
  use Web.Web, :controller
  alias Ping
  @endpoints [
    "https://owlet-api.herokuapp.com",
    "http://www.google.com",
    "https://owlet-api.herokuapp.com",
    "http://www.google.com",
    "https://owlet-api.herokuapp.com",
    "http://www.google.com",
    "https://owlet-api.herokuapp.com",
    "http://www.google.com",
    "https://owlet-api.herokuapp.com",
    "http://www.google.com",
    "https://owlet-api.herokuapp.com",
    "http://www.google.com"
  ]

  def index(conn, _params) do
    tasks = Enum.map(@endpoints, fn endpoint ->
      task = Task.async(fn -> Ping.get(endpoint) end)
      [{:task, task}, {:endpoint, endpoint}]
    end)
    responses = Enum.map(tasks, fn task ->
      response = Task.await(Keyword.get(task, :task))
      [
       {:status_code, response.status_code},
       {:endpoint, Keyword.get(task, :endpoint)}
      ]
    end)
    conn
    |> assign(:responses, responses)
    |> render "index.html"
  end
end
