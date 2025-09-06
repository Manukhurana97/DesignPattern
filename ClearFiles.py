import os

path = os.path.join("./")

def deleteFile(dir, fileName):
	os.remove(os.path.join(dir, fileName))

def deleteClassFilesRecursively(directory):
	count = 0

	for root, dir, files in os.walk(directory):
		for file in files:
			if file.endswith('.class'):
				deleteFile(root, file)
				count += 1
			

	return count;

total = 0
for directory in os.listdir(path):
	dir = os.path.join(path, directory)
	
	if os.path.isdir(dir):
		count = deleteClassFilesRecursively(dir)
		total += count
		
		print(count, "files removed from",dir)
print()
print(total, " total class file deleted")



