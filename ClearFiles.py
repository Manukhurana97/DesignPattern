import os
import shutil

ROOT_PATH = os.path.dirname(os.path.abspath(__file__))
FOLDERS_TO_DELETE = {".ideaa", ".vscode", ".out"}

total_class_files = 0
total_folders_deleted = 0

for root, dirs, files in os.walk(ROOT_PATH):
    # 🔴 Skip .git itself but clean inside projects
    if ".git" in dirs:
        dirs.remove(".git")

    # 1️⃣ Delete .idea / .vscode folders
    for d in list(dirs):  # list() because we mutate dirs
        if d in FOLDERS_TO_DELETE:
            folder_path = os.path.join(root, d)
            shutil.rmtree(folder_path)
            dirs.remove(d)  # prevent further walk
            total_folders_deleted += 1
            print(f"🗑️ Deleted folder: {folder_path}")

    # 2️⃣ Delete .class files
    for file in files:
        if file.endswith(".class"):
            file_path = os.path.join(root, file)
            os.remove(file_path)
            total_class_files += 1
            print(f"❌ Deleted class file: {file_path}")

print("\n✅ Cleanup completed")
print(f"📦 Total folders deleted (.idea/.vscode/.out): {total_folders_deleted}")
print(f"📄 Total .class files deleted: {total_class_files}")
