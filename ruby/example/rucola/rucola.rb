require 'rucola'
require 'stopwatch'

input = ARGV[0] || '../data/sample_input_long_abstracts_sk.ttl'
limit = ARGV[1] || 10
marsh = ARGV[2] || false

watch = Stopwatch.new

if marsh != 'load'
  watch.start "Parsing input data"

  raw = `head -n #{limit} #{input} | script/parsers/long_abstracts_ttl.rb`.split(/\n/).reject(&:empty?)
  data = Hash[raw.map { |item| item.partition(' ').tap { |parts| parts.delete_at 1 }}]

  watch.stop

  model = Rucola::Models::NaiveVectorSpace.new
  extractor = Rucola::Extractors::Chain.new [Rucola::Extractors::Splitter.new(/[[:space:]]+/), Rucola::Extractors::Deletor.new(/[^[[:alnum:]]]/)]
  weighter = Rucola::Weighters::TfIdf
  metric = Rucola::Metrics::NaiveCosineSimilarity

  corpus = Rucola::Corpus.new(model, extractor, weighter, metric)

  watch.measure "Adding documents to corpus" do
    data.each { |link, text| corpus.add text, link: link, text: text }
  end
else
  watch.start "Loading corpus"

  corpus = Marshal.load(File.read "#{input}.corpus")

  watch.stop
end

# only corpus.metric can be changed on loaded corpus, other attributes cannot
# corpus.metric = Rucola::Metrics::TfIdfCosineSimilarity
# corpus.metric = Rucola::Metrics::DiceSimilarity
# corpus.metric = Rucola::Metrics::SizeDifference

puts "Corpus:"
puts "  model = #{corpus.model}"
puts "  extractor = #{corpus.extractor}"
puts "  weighter = #{corpus.weighter}"
puts "  metric = #{corpus.metric}"
puts "  size = #{corpus.size}"

if marsh == 'dump'
  watch.measure "Dumping corpus" do
    File.open("#{input}.corpus", 'w') { |file| file.write(Marshal.dump corpus) }
  end
end

watch.start "Finding similar documents"

sample = corpus.documents.sample
similar = corpus.similar(sample.text)

watch.stop

puts "Documents similar to #{sample.link}:"

similar.sort.reverse.each do |similarity, document|
  puts "  #{'%.12f' % similarity} #{document.link} #{document.text[0..80].gsub(/\s[[:alnum:]]+\s*$/, '…')}"
end

