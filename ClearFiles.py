import os
import shutil

ROOT_PATH = "./"
FOLDERS_TO_DELETE = {".idea", ".vscode"}

def delete_class_files_recursively(directory):
    count = 0
    for root, dirs, files in os.walk(directory):
        for file in files:
            if file.endswith(".class"):
                os.remove(os.path.join(root, file))
                count += 1
    return count


# 1️⃣ Delete .idea and .vscode completely
for folder in FOLDERS_TO_DELETE:
    folder_path = os.path.join(ROOT_PATH, folder)
    if os.path.isdir(folder_path):
        shutil.rmtree(folder_path)
        print(f"Deleted folder: {folder_path}")

# 2️⃣ Delete .class files from remaining directories
total = 0
for entry in os.listdir(ROOT_PATH):
    entry_path = os.path.join(ROOT_PATH, entry)

    if os.path.isdir(entry_path):
        count = delete_class_files_recursively(entry_path)
        total += count
        print(count, "class files removed from", entry_path)

print()
print(total, "total class files deleted")
