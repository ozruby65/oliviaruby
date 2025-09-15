from flask import Flask, render_template
import psycopg2
from config import DATABASE_URI
import random
from faker import Faker



app = Flask(__name__)

    
@app.route("/insert")    
def insert(count=10):
    
    conn = get_db_connection()
    cur = conn.cursor()

   # cur = conn.cursor()
    fake = Faker()

    categories = ["Chair", "Table", "Sofa", "Bed", "Lamp"]
    styles = ["Modern", "Vintage", "Industrial", "Boho", "Classic"]
    colors = ["White", "Black", "Beige", "Navy", "Gray", "Green"]

    for _ in range(count):
        name = fake.word().capitalize() + " " + random.choice(categories)
        category = random.choice(categories)
        style = random.choice(styles)
        color = random.choice(colors)
        price = round(random.uniform(50, 1000), 2)
        description = fake.sentence(nb_words=12)

        cur.execute("""
            INSERT INTO ying_rooms.item_model (name, category, style, color, default_price, description)
            VALUES (%s, %s, %s, %s, %s, %s)
        """, (name, category, style, color, price, description))

    conn.commit()
    print(f"Inserted {count} dummy rows into ying_rooms.item_model.")
    cur.close()
    conn.close()





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


@app.route("/view")
def view():
    conn = get_db_connection()
    cur = conn.cursor()

    # Fetch all rows from your item_model table
    cur.execute("""
        SELECT name, category, style, color, default_price, description
        FROM ying_rooms.item_model
        ORDER BY id DESC
        LIMIT 20
    """)
    rows = cur.fetchall()

    cur.close()
    conn.close()

    # Pass the rows to a template
    return render_template("view.html", items=rows)


if __name__ == "__main__":
    app.run(debug=True)

