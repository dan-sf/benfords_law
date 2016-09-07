from flask import Flask, jsonify, render_template
import subprocess
import ast

app = Flask(__name__)

@app.route('/')
def benfords_law():
    # Run backend java setting var sample and evaluate output string
    data = subprocess.check_output(['java', 'BenfordsLawData', '1000'])
    data = ast.literal_eval(data)

    return render_template("benfords_law.html", data = data)

if __name__ == '__main__':
    app.run()

