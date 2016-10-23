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
    status_codes = Enum.map(@endpoints, fn endpoint -> 
      Ping.get(endpoint, [stream_to: self]).status_code
    end)
    conn
    |> assign(:status_codes, status_codes)
    |> render "index.html"
  end
end
