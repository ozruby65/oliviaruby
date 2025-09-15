from flask import Flask, render_template
import psycopg2
from config import DATABASE_URI

app = Flask(__name__)

def get_db_connection():
    conn = psycopg2.connect(DATABASE_URI)
    return conn

@app.route("/")
def index():
    conn = get_db_connection()
    cur = conn.cursor()
    cur.execute("SELECT version();")
    version = cur.fetchone()
    cur.close()
    conn.close()
    return render_template("index.html", version=version[0])

if __name__ == "__main__":
    app.run(debug=True)
