using System;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

class ImageProfile
{
    static async Task Main()
    {
        var apiKey = Environment.GetEnvironmentVariable("AVATARLOOKUP_API_KEY")
            ?? throw new Exception("Set AVATARLOOKUP_API_KEY");
        using var client = new HttpClient();
        client.DefaultRequestHeaders.Add("X-API-Key", apiKey);
        var content = new StringContent("{\"product\":\"image_profile\",\"image_url\":\"https://example.com/portrait.jpg\"}", Encoding.UTF8, "application/json");
        var response = await client.PostAsync("https://avatarlookup.com/api/v1/image/profile", content);
        var body = await response.Content.ReadAsStringAsync();
        response.EnsureSuccessStatusCode();
        Console.WriteLine(body);
    }
}
