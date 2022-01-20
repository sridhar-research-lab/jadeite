filename='text.txt'
n=1
while read line; do
FILE=$line
if test -f "$FILE"; then
    echo "$FILE exists."
else
    echo "$FILE" >> existed.txt
fi
n=$((n+1))
done < $filename
