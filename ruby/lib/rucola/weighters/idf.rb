module Rucola::Weighters
  module Idf
    extend self

    Infinity = 1.0 / 0.0

    def compute(term, document, corpus)
      idf = Math.log(corpus.size.to_f / corpus.documents.count { |document| document.terms.include? term }.to_f)

      idf == 0 ? Infinity : idf
    end
  end
end

