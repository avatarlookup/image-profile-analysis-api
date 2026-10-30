<?php
$key = getenv('AVATARLOOKUP_API_KEY');
if (!$key) {
    throw new Exception('Set AVATARLOOKUP_API_KEY');
}
$body = json_encode(['service_type' => 'image_profile', 'image_url' => 'https://example.com/portrait.jpg'], JSON_THROW_ON_ERROR);
$curl = curl_init('https://avatarlookup.com/api/v1/image/profile');
curl_setopt_array($curl, [
    CURLOPT_POST => true,
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_HTTPHEADER => ['X-API-Key: '.$key, 'Content-Type: application/json'],
    CURLOPT_POSTFIELDS => $body,
]);
$response = curl_exec($curl);
$status = curl_getinfo($curl, CURLINFO_HTTP_CODE);
if ($response === false || $status >= 300) {
    throw new Exception($response ?: curl_error($curl));
}
echo $response, PHP_EOL;
