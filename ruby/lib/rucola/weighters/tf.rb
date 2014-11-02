module Rucola::Weighters
  module Tf
    extend self

    def compute(term, document, corpus = nil)
      term_count = document.terms.count(term)

      term_count.to_f / document.terms.size.to_f
    end
  end
end

