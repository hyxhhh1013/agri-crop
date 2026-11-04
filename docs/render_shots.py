# -*- coding: utf-8 -*-
from pathlib import Path
from PIL import Image, ImageDraw, ImageFont

DOCS = Path(__file__).resolve().parent
FONT = ImageFont.truetype(r"C:\Windows\Fonts\msyh.ttc", 18)
files = [
    "00_startup.txt",
    "01_list_no_filter.txt",
    "02_list_dynamic.txt",
    "03_get.txt",
    "04_add.txt",
    "05b_growth_cycle.txt",
    "06_update.txt",
    "07_delete.txt",
    "08_deleted_missing.txt",
]
out_dir = DOCS / "images"
out_dir.mkdir(exist_ok=True)

def wrap(text, width=92):
    lines = []
    for raw in text.splitlines():
        if not raw:
            lines.append("")
            continue
        while len(raw) > width:
            lines.append(raw[:width])
            raw = raw[width:]
        lines.append(raw)
    return lines

for name in files:
    text = (DOCS / name).read_text(encoding="utf-8").rstrip()
    lines = wrap(text)
    line_h = 26
    width = 1100
    height = 48 + line_h * len(lines) + 20
    img = Image.new("RGB", (width, height), "#0f1720")
    draw = ImageDraw.Draw(img)
    draw.rectangle((0, 0, width, 36), fill="#16324f")
    draw.text((16, 6), name.replace(".txt", ""), font=FONT, fill="#d6e6ff")
    y = 48
    for line in lines:
        draw.text((16, y), line, font=FONT, fill="#d7e0ea")
        y += line_h
    img.save(out_dir / (Path(name).stem + ".png"))
    print(name, height)
