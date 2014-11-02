module Rucola
  Corpus = Struct.new(:model, :extractor, :weighter, :metric) do
    attr_reader :documents

    def initialize(*)
      super

      @documents = []
    end

    def add(text, attrs = {})
      document = new_document(text, attrs)

      documents << document

      model.add self, document
    end

    def similar(text, attrs = {})
      model.similar self, new_document(text, attrs)
    end

    def size
      documents.size
    end

    private

    def new_document(text, attrs = {})
      terms = extractor.extract text

      Document.new(attrs.merge terms: terms)
    end
  end
end

