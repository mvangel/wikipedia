#!/usr/bin/env ruby

STDIN.each_line do |line|
  next if line =~ /\A\W?\#/

  data = line.strip.match(/\<([\:\/\.\_[:alpha:]]+)\>\s\<([\:\/\.\_[:alpha:]]+)\>\s\"(.+)\"\@sk\s\./)

  STDOUT << "#{data[1]} #{data[3]}\n"
end

