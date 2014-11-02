require 'matrix'
require 'ostruct'
require 'set'

require 'rucola/base'
require 'rucola/corpus'
require 'rucola/document'
require 'rucola/extractors/chain'
require 'rucola/extractors/character'
require 'rucola/extractors/deletor'
require 'rucola/extractors/splitter'
require 'rucola/metrics/dice_similarity'
require 'rucola/metrics/naive_cosine_similarity'
require 'rucola/metrics/size_difference'
require 'rucola/metrics/tf_idf_cosine_similarity'
require 'rucola/models/naive_vector_space'
require 'rucola/weighters/tf'
require 'rucola/weighters/idf'
require 'rucola/weighters/tf_idf'

module Rucola
end

