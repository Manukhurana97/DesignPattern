import subprocess
import sys
import shlex
from datetime import datetime

def run_command(cmd):
    result = subprocess.run(cmd, shell=True)
    if result.returncode != 0:
        print(f"❌ Error running command: {cmd}")
        sys.exit(result.returncode)

def has_staged_changes():
    return subprocess.run("git diff --cached --quiet", shell=True).returncode != 0

def main():
    # Combine all CLI args for full commit message
    if len(sys.argv) > 1:
        commit_msg = " ".join(sys.argv[1:])
    else:
        commit_msg = input("Enter commit message: ").strip()
        if not commit_msg:
            commit_msg = datetime.now().strftime("%Y-%m-%d")

    print("📥 Pulling latest changes...")
    run_command("git pull")

    print("📦 Adding all changes...")
    run_command("git add .")

    if not has_staged_changes():
        print("⚠️  No changes to commit.")
        sys.exit(0)

    print(f"📝 Committing with message: '{commit_msg}'")
    run_command(f'git commit -m {shlex.quote(commit_msg)}')

    print("📤 Pushing to remote...")
    run_command("git push")

    print("✅ Done.")

if __name__ == "__main__":
    main()
