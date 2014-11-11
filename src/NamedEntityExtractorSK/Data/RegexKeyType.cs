namespace NamedEntityExtractorSK.Data
{
	public struct RegexKeyType
	{
		public string Value;
		public NamedEntityType Type;

		public RegexKeyType(string value, NamedEntityType type)
		{
			this.Value = value;
			this.Type = type;
		}
	}
}
