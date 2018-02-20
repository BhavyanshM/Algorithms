import subprocess

p = subprocess.Popen('/mnt/c/Program\ Files/Java/jre1.8.0_144/bin/java.exe -jar Planner.jar', shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)

stdout, stderr = p.communicate(stdin)

for i in range(10):
	p.stdin.write(b"'hi'.format(i)")
	p.stdin.write(b'\n')
	print("This is ", stdout)
