import subprocess

p = subprocess.Popen('/mnt/c/Program\ Files/Java/jre1.8.0_144/bin/java.exe -jar Planner.jar', shell=True, stdin=subprocess.PIPE, stdout=subprocess.PIPE)

outs = b'haha\n'

p.stdin.write(outs)

print("i am done")
print(p.stdout.readline().rstrip())
