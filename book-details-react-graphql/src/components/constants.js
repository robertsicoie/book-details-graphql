import { gql } from '@apollo/client';

export const QUERY_AUTHORS = gql`
{
  authors {
    id
    firstName
    lastName
  }
}
`;

export const QUERY_BOOKS = gql`
{
  books {
    id
    name
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}`;

export const ADD_BOOK = gql`
mutation PostMutation(
    $name: String!
    $pageCount: Int
    $authorId: String
) {
    addBook(name: $name, pageCount:$pageCount, authorId: $authorId) {
      name
      pageCount
      author {
        firstName
        lastName
      }
    }
}`;