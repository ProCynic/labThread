set term pdf
set output "plot3c.pdf"

plot "data3c" using 1:2 with lines, "data3c" using 1:3 with lines, "data3c" using 1:4 with lines, "data3c" using 1:5 with lines, "data3c" using 1:6 with lines, "data3c" using 1:7 with lines
