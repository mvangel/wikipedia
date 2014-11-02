module Rucola
  class Document < OpenStruct
    def size
      terms.size
    end
  end
end

