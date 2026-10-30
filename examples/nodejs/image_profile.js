const apiKey = process.env.AVATARLOOKUP_API_KEY;
if (!apiKey) throw new Error("Set AVATARLOOKUP_API_KEY");

const baseUrl = process.env.API_BASE_URL || "https://avatarlookup.com";
const imageUrl = process.env.IMAGE_URL || "https://example.com/portrait.jpg";

const response = await fetch(`${baseUrl}/api/v1/image/profile`, {
  method: "POST",
  headers: { "X-API-Key": apiKey, "Content-Type": "application/json" },
  body: JSON.stringify({ service_type: "image_profile", image_url: imageUrl }),
});
if (!response.ok) throw new Error(await response.text());
const result = await response.json();
if (result.code !== 0) throw new Error(JSON.stringify(result));
console.log(result);
