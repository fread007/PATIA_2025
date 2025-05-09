import re

input_file = "truc"
output_file = "move.txt"
move = ""

with open(input_file, "r") as fin, open(output_file, "w") as fout:
    for line in fin:
        # Cherche le nom d'action entre parenthèses après le numéro
        match = re.search(r"\(\s*(\w+)", line)
        if match:
            action = match.group(1).strip().lower()
            if action.startswith("push") or action.startswith("move"):
                if action == "pushu" or action == "moveu":
                    move +="U"
                elif action == "pushd" or action == "moved":
                    move +="D"
                elif action == "pushl" or action == "movel":
                    move +="L"
                elif action == "pushr" or action == "mover":
                    move +="R"
    fout.write(move)
print(f"Mouvements extraits dans {output_file}")