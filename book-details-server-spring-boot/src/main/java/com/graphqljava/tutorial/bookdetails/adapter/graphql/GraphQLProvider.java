package com.graphqljava.tutorial.bookdetails.adapter.graphql;

import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Resources;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {


    @Autowired
    GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))

                .type(newTypeWiring("InputAuthor")
                    .dataFetcher("author", graphQLDataFetchers.addAuthorDataFetcher()))

                .type(newTypeWiring("Query")
                    .dataFetchers(ImmutableMap.of(
                        "authors", graphQLDataFetchers.getAuthorDataFetcher(),
                        "books", graphQLDataFetchers.getBookDataFetcher())))

                .type(newTypeWiring("Mutation")
                    .dataFetchers(ImmutableMap.of(
                        "addBook", graphQLDataFetchers.addBookDataFetcher(),
                        "updateBook", graphQLDataFetchers.updateBookDataFetcher(),
                        "addAuthor", graphQLDataFetchers.addAuthorDataFetcher(),
                        "updateAuthor", graphQLDataFetchers.updateAuthorDataFetcher())))

                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

}
