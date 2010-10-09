set term pdf
set output "plot3b.pdf"

plot "data3b" using 1:2 with lines, "data3b" using 1:3 with lines, "data3b" using 1:4 with lines, "data3b" using 1:5 with lines, "data3b" using 1:6 with lines, "data3b" using 1:7 with lines
